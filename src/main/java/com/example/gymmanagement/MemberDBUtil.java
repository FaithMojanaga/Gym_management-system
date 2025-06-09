package com.example.gymmanagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDBUtil {
    private final Connection connection;

    public MemberDBUtil(Connection connection) {
        this.connection = connection;
    }

    // Add a member
    public void addMember(Member member) throws SQLException {
        String query = "INSERT INTO member (first_name, last_name, membership_type, phone, email) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setString(3, member.getMembershipType());
            stmt.setString(4, member.getPhone());
            stmt.setString(5, member.getEmail());
            stmt.executeUpdate();
        }
    }

    // Get all members
    public List<Member> getAllMembers() throws SQLException {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM member";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Member member = mapResultSetToMember(rs);
                members.add(member);
            }
        }
        return members;
    }

    // Get members by membership type
    public List<Member> getMembersByMembershipType(String membershipType) throws SQLException {
        List<Member> members = new ArrayList<>();
        String query = "SELECT * FROM member WHERE membership_type = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, membershipType);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Member member = mapResultSetToMember(rs);
                    members.add(member);
                }
            }
        }
        return members;
    }

    // Get member by ID
    public Member getMemberById(int id) throws SQLException {
        Member member = null;
        String query = "SELECT * FROM member WHERE member_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    member = mapResultSetToMember(rs);
                }
            }
        }
        return member;
    }

    // Update member details
    public void updateMember(Member member) throws SQLException {
        String query = "UPDATE member SET first_name = ?, last_name = ?, membership_type = ?, phone = ?, email = ? WHERE member_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, member.getFirstName());
            stmt.setString(2, member.getLastName());
            stmt.setString(3, member.getMembershipType());
            stmt.setString(4, member.getPhone());
            stmt.setString(5, member.getEmail());
            stmt.setInt(6, member.getMemberId());
            stmt.executeUpdate();
        }
    }

    // Delete member by ID
    public void deleteMember(int id) throws SQLException {
        String query = "DELETE FROM member WHERE member_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Helper method to map ResultSet to Member
    private Member mapResultSetToMember(ResultSet rs) throws SQLException {
        int memberId = rs.getInt("member_id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String phone = rs.getString("phone");
        String membershipType = rs.getString("membership_type");
        String email = rs.getString("email");

        // Create member object and set the details
        Member member = new Member(memberId, firstName, lastName, phone, membershipType, email);
        return member;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
