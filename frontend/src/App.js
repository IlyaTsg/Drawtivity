import './App.css';
import React from "react";
import SignUp from "./components/Authorization/SignUpPage/components/SignUp";
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import SignIn from "./components/Authorization/SignInPage/components/SignIn";
import Header from "./components/Header/components/Header";
import Info from "./components/Info/Components/Info";


function App() {
  return (
      <BrowserRouter>
          <Header/>
          <Routes>
              <Route path={'/info'} element={<Info/>}/>
              <Route path={'/sign_up'} element={<SignUp/>}/>
              <Route path={'sign_in'} element={<SignIn/>}/>
              <Route path={'/*'} element={<Navigate to={'/info'} replace/>}/>
          </Routes>
      </BrowserRouter>
  );
}

export default App;
