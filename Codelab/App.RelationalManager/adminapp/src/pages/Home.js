import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function Home() {
    const [users, setUsers] = useState([]);

    // React hook to fetch the users data
    useEffect(() => {
        axios.get('http://localhost:8080/api/users')
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the users!', error);
            });
    }, []);

    return (
        <div className="container mt-5">
            <h1 className="mb-4">Listing of Users</h1>
            <div className="mb-4">
                <Link to="/adduser" className="btn btn-primary">Add User</Link>
            </div>
            <table className="table table-striped table-bordered">
                <thead className="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Age</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map(user => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.fullName}</td>
                            <td>{user.phone}</td>
                            <td>{user.email}</td>
                            <td>{user.age}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Home;
