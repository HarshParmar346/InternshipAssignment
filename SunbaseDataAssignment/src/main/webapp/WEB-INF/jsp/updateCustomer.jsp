<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 400px;
            text-align: center;
            display: grid;
            gap: 10px;
            margin: auto;
        }

        input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            cursor: pointer;
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-top: 10px;
        }

        .row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 10px;
        }

        div {
            text-align: left;
            margin-bottom: 10px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <form action="/update/${uuid}" method="post">
        <h2>Update Customer</h2>
        <div class="row">
            <div>
                <label for="first_name">First Name:</label>
                <input id="first_name" name="first_name" value="${customer.getFirst_name()}" type="text">
            </div>
            <div>
                <label for="last_name">Last Name:</label>
                <input id="last_name" name="last_name" value="${customer.getLast_name()}" type="text">
            </div>
        </div>
        <div class="row">
            <div>
                <label for="street">Street:</label>
                <input id="street" name="street" value="${customer.getStreet()}" type="text">
            </div>
            <div>
                <label for="address">Address:</label>
                <input id="address" name="address" value="${customer.getAddress()}" type="text">
            </div>
        </div>
        <div class="row">
            <div>
                <label for="city">City:</label>
                <input id="city" name="city" value="${customer.getCity()}" type="text">
            </div>
            <div>
                <label for="state">State:</label>
                <input id="state" name="state" value="${customer.getState()}" type="text">
            </div>
        </div>
        <div class="row">
            <div>
                <label for="email">Email:</label>
                <input id="email" name="email" value="${customer.getEmail()}" type="email">
            </div>
            <div>
                <label for="phone">Phone:</label>
                <input id="phone" name="phone" value="${customer.getPhone()}" type="tel">
            </div>
        </div>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
