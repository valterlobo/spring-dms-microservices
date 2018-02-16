<html>
<head>
    <title>Login</title>
    <style>
        @import url(https://fonts.googleapis.com/css?family=Roboto:300);

        .login-page {
            width: 360px;
            padding: 8% 0 0;
            margin: auto;
        }

        .form {
            position: relative;
            z-index: 1;
            background: #FFFFFF;
            max-width: 360px;
            margin: 0 auto 100px;
            padding: 45px;
            text-align: center;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
        }

        .form input {
            font-family: "Roboto", sans-serif;
            outline: 0;
            background: #f2f2f2;
            width: 100%;
            border: 0;
            margin: 0 0 15px;
            padding: 15px;
            box-sizing: border-box;
            font-size: 14px;
        }

        .form button {
            font-family: "Roboto", sans-serif;
            text-transform: uppercase;
            outline: 0;
            background: #0066ff;
            width: 100%;
            border: 0;
            padding: 15px;
            color: #FFFFFF;
            font-size: 14px;
            -webkit-transition: all 0.3 ease;
            transition: all 0.3 ease;
            cursor: pointer;
        }

        .form button:hover, .form button:active, .form button:focus {
            background: #3333cc;
        }

        .form .message {
            margin: 15px 0 0;
            color: #b3b3b3;
            font-size: 12px;
        }

        .form .message a {
            color: #4CAF50;
            text-decoration: none;
        }

        .form .register-form {
            display: none;
        }

        .container {
            position: relative;
            z-index: 1;
            max-width: 300px;
            margin: 0 auto;
        }

        .container:before, .container:after {
            content: "";
            display: block;
            clear: both;
        }

        .container .info {
            margin: 50px auto;
            text-align: center;
        }

        .container .info h1 {
            margin: 0 0 15px;
            padding: 0;
            font-size: 36px;
            font-weight: 300;
            color: #1a1a1a;
        }

        .container .info span {
            color: #4d4d4d;
            font-size: 12px;
        }

        .container .info span a {
            color: #000000;
            text-decoration: none;
        }

        .container .info span .fa {
            color: #EF3B3A;
        }

        body {
            background: #05386B; /* fallback for old browsers */
            background: -webkit-linear-gradient(right, #05386B, #5cdb95);
            background: -moz-linear-gradient(right, #05386B, #5cdb95);
            background: -o-linear-gradient(right, #05386B, #5cdb95);
            background: linear-gradient(to left, #05386B, #5cdb95);
            font-family: "Roboto", sans-serif;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }
    </style>
</head>
<body>
<div class="login-page">
    <div class="form">
    <#if RequestParameters.error??>
        <div class="alert alert-danger" align="center">
            <strong>Invalid username or password</strong><br>
        </div>
    <#elseif RequestParameters.logout??>
        <div class="alert alert-info" align="center">
            <strong>Logged out!</strong><br>You have Logged out of DMS
        </div>
    </#if>
        <form class="login-form" action="login" method="post">
            <input type="text" name="username" placeholder="username" autofocus/>
            <input type="password" name="password" placeholder="password"/>
            <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
        </form>
    </div>
</div>
</body>
</html>
