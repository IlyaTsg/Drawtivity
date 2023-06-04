import './App.css';
import React from "react";
import SignUp from "./pages/ui/Authorization/SignUpPage/components/SignUp";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import SignIn from "./pages/ui/Authorization/SignInPage/components/SignIn";
import Header from "./entities/ui/Header/components/Header";
import Info from "./pages/ui/Info/Components/Info";
import {Provider} from "react-redux";
import store from "./store";
import Constructor from "./pages/ui/Constructor/components/Constructor";
import EditTaskPage from "./pages/ui/EditTaskPage/components/EditTaskPage";
import Tasks from "./pages/ui/Tasks/components/Tasks";
import TaskPage from "./pages/ui/TaskPage/components/TaskPage";
import DocumentationPage from "./pages/ui/Documentation/components/DocumentationPage";


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
