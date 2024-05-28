const express = require('express');
const WebSocket = require('ws');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 4000;


app.use(cors());
// Настройка body-parser для обработки JSON данных
app.use(bodyParser.json());

// Настройка body-parser для обработки application/x-www-form-urlencoded данных
app.use(bodyParser.urlencoded({ extended: true }));

// Создаем сервер HTTP
const server = require('http').createServer(app);

// Создаем WebSocket сервер
const wss = new WebSocket.Server({ server });

// Храним подключенные клиенты WebSocket
let clients = [];

// Обработка подключения WebSocket
wss.on('connection', (ws) => {
  console.log('Client connected');
  clients.push(ws);

  // Удаляем клиента из списка при закрытии соединения
  ws.on('close', () => {
    clients = clients.filter(client => client !== ws);
  });
});

// Маршрут для обработки POST-запросов
app.post('/send-message', (req, res) => {
  const message = req.body;
  console.log(req.body);
  // Отправляем сообщение всем подключенным клиентам
  setTimeout(() => {
    clients.forEach(client => {
      if (client.readyState === WebSocket.OPEN) {
        client.send(JSON.stringify({ message }));
      }
    });
  }, 4000);

  const redirectUrl = `http://localhost:${3000}/lti_task/${+req.body.custom_task_id}`;
  res.redirect(redirectUrl);
});

// Запуск сервера
server.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});