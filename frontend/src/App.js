import './App.css';
import React, { useEffect, useState } from 'react';
import SignUp from './pages/ui/Authorization/SignUpPage/components/SignUp';
import { BrowserRouter, Navigate, Route, Routes, useNavigate } from 'react-router-dom';
import SignIn from './pages/ui/Authorization/SignInPage/components/SignIn';
import Header from './entities/ui/Header/components/Header';
import Info from './pages/ui/Info/Components/Info';
import { Provider } from 'react-redux';
import store from './entities/model/store';
import Constructor from './pages/ui/Constructor/components/Constructor';
import EditTaskPage from './pages/ui/EditTaskPage/components/EditTaskPage';
import Tasks from './pages/ui/Tasks/Сomponents/Tasks';
import TaskPage from './pages/ui/TaskPage/components/TaskPage';
import DocumentationPage from './pages/ui/Documentation/components/DocumentationPage';
import TaskBlock from './widgets/ui/TaskBlock/TaskBlock';
import { getTasks } from './entities/model/store/slices/tasksSlice';
import { TaskApi } from './pages/api/TaskApi';
import LtiSolution from './pages/lti/ui/LtiSolution/LtiSolution';


function App() {
  //store.dispatch(getTasks())
  const [messages, setMessages] = useState([]);
  const nav = useNavigate();

  useEffect(() => {
    const socket = new WebSocket('ws://localhost:4000');

    socket.onopen = () => {
      console.log('WebSocket connection established with ws://localhost:4000');
    };

    socket.onmessage = (event) => {
      const data = JSON.parse(event.data);
      console.log(event.data, data);
      /*const ltiCheck = async () => {
        const resp = await TaskApi.ltiCheckRequest(event.data);
        if (resp.status === 200) {
          setMessages(prevMessages => [...prevMessages, data.message]);
          nav(`/task/:${resp.data.applicationContext.id}`);
        }
      };
      ltiCheck();*/
    };

    return () => {
      socket.close();
    };
  }, []);
  return (
    <Provider store={store}>
      <Routes>
        <Route path={'/info'} element={<><Header /><Info /></>} />
        <Route path={'/sign_up'} element={<><Header /><SignUp /></>} />
        <Route path={'/sign_in'} element={<><Header /><SignIn /></>} />
        <Route path={'/constructor'} element={<><Header /><Constructor /></>} />
        <Route path={'/edit_task/:id'} element={<><Header /><EditTaskPage /></>} />
        <Route path={'/tasks'} element={<><Header /><Tasks /></>} />
        <Route path={'/task/:id'} element={<><Header /><TaskPage /></>} />
        <Route path={'/documentation'} element={<><Header /><DocumentationPage /></>} />
        <Route path={'/*'} element={<Navigate to={'/info'} replace />} />
        <Route path={`/task/:id}`} element={<><Header /><TaskBlock /></>} />
        <Route path={`/lti_task/:id`} element={<LtiSolution />} />
      </Routes>

    </Provider>
  );
}

export default App;
