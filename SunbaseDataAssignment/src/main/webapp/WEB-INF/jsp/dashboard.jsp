<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer List</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        form {
            display: inline-block;
            margin: 0;
        }

        button {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
    </style>
    <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this customer?");
        }
    </script>
</head>
<body>

    <form action="/newCustomer" method="get">
        <button type="submit">Add New Customer</button>
    </form>
    
    <h2>List of Customers</h2>
    <table>
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Street</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Delete/Edit</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="listItem">
                <tr>
                    <td>${listItem.getFirst_name()}</td>
                    <td>${listItem.getLast_name()}</td>
                    <td>${listItem.getStreet()}</td>
                    <td>${listItem.getAddress()}</td>
                    <td>${listItem.getCity()}</td>
                    <td>${listItem.getState()}</td>
                    <td>${listItem.getEmail()}</td>
                    <td>${listItem.getPhone()}</td>
                    <td>
                        <form action="/update/${listItem.getUuid()}">
                            <input type="hidden" id="first_name" name="first_name" value="${listItem.getFirst_name()}"> 
                            <input type="hidden" id="last_name" name="last_name" value="${listItem.getLast_name()}">
                            <input type="hidden" id="street" name="street" value="${listItem.getStreet()}">
                            <input type="hidden" id="address" name="address" value="${listItem.getAddress()}"> 
                            <input type="hidden" id="city" name="city" value="${listItem.getCity()}"> 
                            <input type="hidden" id="state" name="state" value="${listItem.getState()}">
                            <input type="hidden" id="email" name="email" value="${listItem.getEmail()}">
                            <input type="hidden" id="phone" name="phone" value="${listItem.getPhone()}">
                            <button type="submit">Update</button>
                        </form>

                        <form action="/delete/${listItem.getUuid()}" method="post" onsubmit="return confirmDelete();">
                            <button type="submit" style="background-color: #f44336;">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
