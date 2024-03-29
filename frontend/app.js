const express = require("express");
const path = require("path");
const app = express();

app.listen(3000, () => {
  var currentDateTime = new Date();
  var formattedDateTime = currentDateTime.toLocaleTimeString(); // formatuje czas do postaci HH:MM:SS

  console.log(formattedDateTime + ' Application started and Listening on port 3000');
});
const loginPageCommonCss = path.join(__dirname, "src");
app.use(express.static(loginPageCommonCss));

//pages
app.get("/login", (req, res) => {
    const loginPagePath = path.join(__dirname, "src", "pages", "login", "loginPage.html");
    res.sendFile(loginPagePath);
});

app.get("/main", (req, res) => {
    const mainPagePath = path.join(__dirname, "src", "pages", "main", "mainPage.html");
    res.sendFile(mainPagePath);
});

app.get("/settings", (req, res) => {
  const mainPagePath = path.join(__dirname, "src", "pages", "settings", "settingsPage.html");
  res.sendFile(mainPagePath);
});

app.get("/quizMain", (req, res) => {
  const pagePath = path.join(__dirname, "src", "pages", "quiz", "quizMainPage.html");
  res.sendFile(pagePath);
});

app.get("/addCard", (req, res) => {
  const pagePath = path.join(__dirname, "src", "pages", "quiz", "addCardPage.html");
  res.sendFile(pagePath);
});

app.get("/quiz", (req, res) => {
  const pagePath = path.join(__dirname, "src", "pages", "quiz", "quizPage.html");
  res.sendFile(pagePath);
});

app.get("/quizSettings", (req, res) => {
  const pagePath = path.join(__dirname, "src", "pages", "quiz", "quizSettings.html");
  res.sendFile(pagePath);
});

app.get("/quizCardReview", (req, res) => {
  const pagePath = path.join(__dirname, "src", "pages", "quiz", "quizCardReviewPage.html");
  res.sendFile(pagePath);
});
