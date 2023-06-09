<%@ page import = "jared.simpledatabase.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset-UTF-8">

    <title>Quick Credit | Client Account</title>
      <link rel="stylesheet" href="../../resouces/static/css/style.css">
      <link rel="stylesheet" href="../../resouces/static/css/responsive.css">
</head>
<body>

      <div class="navigation">
              <div class="header">
                  <div class="logo with-bars">
                      <h2><a href="login.html">Quick Credit</a></h2>
                      <div class="tree-bars" onclick="toggleLeftMenu()">
                        <div class="bar1"></div>
                        <div class="bar2"></div>
                        <div class="bar3"></div>
                      </div>
                  </div>
                  <div class="menu">
                      <ul>
                          <li>
                              <a class="account-link" onclick="toggleRightMenu()">
                              <img class="circle profile-pic" src="../../images/default-avatar.png" alt="user">
                              <span class="username"><span class="usernm">N.Herve</span> <i class="caret">&#9660;</i></span>
                              </a>
                              <ul class="drop-content" id="right-menu">
                                  <li><a href="profile.html">My Profile</a></li>
                                  <hr>
                                  <li><a href="../login.html">Logout</a></li>
                              </ul>
                          </li>
                      </ul>
                  </div>
              </div>
          </div>
          <div class="main-content">
              <div class="aside" id="aside">
                  <ul onclick="myFunction(event)">
                      <li><a href="account.html" class="active">My account</a></li>
                      <li><a href="request-a-loan.html">Request a Loan</a></li>
                      <li><a href="requested-loans.html">All requested loans</a></li>
                      <li><a href="repayment-history.html">Repayment history</a></li>
                  </ul>
              </div>
              <div class="content" id="content">
                  <div id="client">
                      <div id="current-pages">
                          <ul class="current-page">
                            <li class="active"> <b>My account | Client ID: #45645678</b>
                              <label class="label green-label">verified</label>
                            </li>
                          </ul>
                      </div>
                      <div class="back-card">
                          <table class="table">
                              <tbody>
                                  <tr>
                                      <td><b>Full Name</b></td>
                                      <td> <span>Herve Nkurikiyimfura</span></td>
                                  </tr>
                                  <tr>
                                      <td><b>Email</b></td>
                                      <td><span>herveralive@gmail.com</span></td>
                                  </tr>
                                  <tr>
                                      <td><b>Address</b></td>
                                      <td>KN Nyamirambo ST 400</td>
                                  </tr>
                                  <tr>
                                      <td><b>Current Loan</b></td>
                                      <td>$345.00</td>
                                  </tr>
                                  <tr>
                                      <td><b>Payed Loan</b></td>
                                      <td>$300.00</td>
                                  </tr>
                                  <tr>
                                      <td><b>Remain</b></td>
                                      <td>$45.00</td>
                                  </tr>
                              </tbody>
                          </table>
                      </div>
                  </div>
              </div>
          </div>
          <footer>
              <div class="copyright">
                   <p>&copy; <script>document.write(new Date().getFullYear());</script> <a href="#">Quick Credit</a> All Rights Reserved</p>
              </div>
          </footer>
          <script src="../../js/script.js"></script>

</body>
</html>