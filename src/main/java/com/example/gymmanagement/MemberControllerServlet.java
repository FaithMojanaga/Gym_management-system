package com.example.gymmanagement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import jakarta.annotation.Resource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "MemberControllerServlet", value = "/MemberControllerServlet")
public class MemberControllerServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(MemberControllerServlet.class.getName());

    private MemberDBUtil memberDBUtil;

    @Resource(name = "jdbc/gym_db")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {
        super.init();
        if (dataSource == null) {
            throw new ServletException("DataSource not injected.");
        }
        try {
            memberDBUtil = new MemberDBUtil(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize MemberDBUtil", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "LIST"; // Default action is to list members
        }

        try {
            switch (action) {
                case "LIST":
                    listMembers(request, response);
                    break;
                case "LOAD":
                    loadMember(request, response);
                    break;
                case "DELETE":
                    deleteMember(request, response);
                    break;
                default:
                    listMembers(request, response); // Default to listing members
                    break;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing request", e);
            throw new ServletException("Error processing request", e);
        }
    }

    private void listMembers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String membershipType = request.getParameter("membershipType");
        logger.log(Level.INFO, "Membership Type Filter: {0}", membershipType);

        List<Member> members;
        if (membershipType != null && !membershipType.isEmpty()) {
            members = memberDBUtil.getMembersByMembershipType(membershipType);
        } else {
            members = memberDBUtil.getAllMembers();
        }

        logger.log(Level.INFO, "Members retrieved: {0}", members.size());
        request.setAttribute("members", members);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list_members.jsp");
        dispatcher.forward(request, response);
    }

    private void loadMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int memberId = Integer.parseInt(request.getParameter("memberId"));
            Member member = memberDBUtil.getMemberById(memberId);

            if (member == null) {
                throw new Exception("Member with ID " + memberId + " not found.");
            }

            request.setAttribute("member", member); // Corrected this to 'member' instead of 'members'
            RequestDispatcher dispatcher = request.getRequestDispatcher("/update_member.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading member", e);
            throw new ServletException("Error loading member", e);
        }
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String memberId = request.getParameter("memberId");
        if (memberId != null) {
            int id = Integer.parseInt(memberId);
            memberDBUtil.deleteMember(id);
            // Add a success message to the session
            request.getSession().setAttribute("message", "Member deleted successfully.");
        }
        // Redirect to the list of members after deletion
        response.sendRedirect(request.getContextPath() + "/MemberControllerServlet?action=LIST");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "ADD":
                    addMember(request, response);
                    break;
                case "UPDATE":
                    updateMember(request, response);
                    break;
                default:
                    listMembers(request, response);
                    break;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing post request", e);
            throw new ServletException("Error processing request", e);
        }
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String membershipType = request.getParameter("membershipType");

        Member newMember = new Member(0, firstName, lastName, phone, membershipType, email);
        memberDBUtil.addMember(newMember);

        response.sendRedirect(request.getContextPath() + "/MemberControllerServlet?action=LIST");
    }

    private void updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int memberId = Integer.parseInt(request.getParameter("memberId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String membershipType = request.getParameter("membershipType");

        Member updatedMember = new Member(memberId, firstName, lastName, phone, membershipType, email);
        memberDBUtil.updateMember(updatedMember);

        response.sendRedirect(request.getContextPath() + "/MemberControllerServlet?action=LIST");
    }

    @Override
    public void destroy() {
        if (memberDBUtil != null) {
            memberDBUtil.closeConnection();
        }
    }
}
