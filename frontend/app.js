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
    const loginPagePath = path.join(__dirname, "src", "pages", "loginPage.html");
    res.sendFile(loginPagePath);
});

app.get("/main", (req, res) => {
    const mainPagePath = path.join(__dirname, "src", "pages", "mainPage.html");
    res.sendFile(mainPagePath);
});

app.get("/settings", (req, res) => {
  const mainPagePath = path.join(__dirname, "src", "pages", "settingsPage.html");
  res.sendFile(mainPagePath);
});

app.get("/quiz", (req, res) => {
  const quizPagePath = path.join(__dirname, "src", "pages", "quiz", "quizPage.html");
  res.sendFile(quizPagePath);
});

app.get("/addCard", (req, res) => {
  const addCardPagePath = path.join(__dirname, "src", "pages", "quiz", "addCard.html");
  res.sendFile(addCardPagePath);
});
