import './App.css';
import React from "react";
import SignUp from "./components/Authorization/SignUpPage/components/SignUp";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import SignIn from "./components/Authorization/SignInPage/components/SignIn";
import Header from "./components/Header/components/Header";
import Info from "./components/Info/Components/Info";
import {Provider} from "react-redux";
import store from "./store";
import Constructor from "./components/Constructor/components/Constructor";
import EditTaskPage from "./components/EditTaskPage/components/EditTaskPage";
import Tasks from "./components/Tasks/components/Tasks";
import TaskPage from "./components/TaskPage/components/TaskPage";
import DocumentationPage from "./components/Documentation/components/DocumentationPage";


function App() {
  return (
      <Provider store={store}>
          <BrowserRouter>
              <Header/>
              <Routes>
                  <Route path={'/info'} element={<Info/>}/>
                  <Route path={'/sign_up'} element={<SignUp/>}/>
                  <Route path={'sign_in'} element={<SignIn/>}/>
                  <Route path={'/constructor'} element={<Constructor/>} />
                  <Route path={'/edit_task/:id'} element={<EditTaskPage/>} />
                  <Route path={'/tasks'} element={<Tasks/>} />
                  <Route path={'/task/:id'} element={<TaskPage/>} />
                  <Route path={'/documentation'} element={<DocumentationPage/>} />
                  <Route path={'/*'} element={<Navigate to={'/info'} replace/>}/>
              </Routes>
          </BrowserRouter>
      </Provider>
  );
}

export default App;
