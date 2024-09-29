import './App.css';
import React from 'react';
import {BrowserRouter as Router, Link, Route, Routes} from 'react-router-dom';
import Home from './pages/Home';
import AddUser from './pages/AddUser';
import AddInvestor from './pages/AddInvestor';
import 'bootstrap/dist/css/bootstrap.min.css';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function App() {
  return (
      <Router>
        <div className="App">
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
              <Link className="navbar-brand" to="/">Relational Manager</Link>
              <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
              </button>
              <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                  <li className="nav-item">
                    <Link className="nav-link" to="/">Home</Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link" to="/adduser">Add User</Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link" to="/addinvestor">Add Investor</Link>
                  </li>
                </ul>
              </div>
            </div>
          </nav>
          <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/adduser" element={<AddUser/>}/>
            <Route path="/addinvestor" element={<AddInvestor/>}/>
          </Routes>
          <ToastContainer />
        </div>
      </Router>
  );
}

export default App;
