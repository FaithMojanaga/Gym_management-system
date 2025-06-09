<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Members List</title>
</head>
<body>

<h2>Members List</h2>

<!-- Success Message -->
<c:if test="${not empty sessionScope.message}">
  <div style="color: green;">
      ${sessionScope.message}
    <c:set var="message" value="" />
  </div>
</c:if>

<!-- Add New Member Button -->
<a href="add_member.jsp" class="btn btn-success"> New Member</a>

<table border="1">
  <thead>
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Membership Type</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="member" items="${members}">
    <tr>
      <td>${member.firstName}</td>
      <td>${member.lastName}</td>
      <td>${member.email}</td>
      <td>${member.phone}</td>
      <td>${member.membershipType}</td>
      <td>
        <a href="<c:url value='/MemberControllerServlet?action=LOAD&memberId=${member.memberId}' />">Edit</a> |
        <a href="<c:url value='/MemberControllerServlet?action=DELETE&memberId=${member.memberId}' />" onclick="return confirm('Are you sure you want to delete this member?');">Delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

<br>
<a href="<c:url value='/MemberControllerServlet?action=LIST' />">Refresh List</a>

</body>
</html>
