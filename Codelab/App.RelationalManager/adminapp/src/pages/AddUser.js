import React, { useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

function AddUser() {
    const [formData, setFormData] = useState({
        username: '',
        firstname: '',
        lastname: '',
        phone: '',
        email: '',
        age: ''
    });

    const notifySuccess = () => {
        toast.success('User added successfully!', {
            // position: toast.POSITION.TOP_RIGHT,
            autoClose: 5000,
        });
    };

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const validate = () => {
        let tempErrors = {};
        tempErrors.username = formData.username ? "" : "Username is required.";
        tempErrors.phone = formData.phone ? (formData.phone.match(/^\d{10}$/) ? "" : "Phone must be 10 digits.") : "Phone is required.";
        setErrors(tempErrors);
        return Object.values(tempErrors).every(x => x === "");
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (validate()) {
            try {
                const response = await axios.post('http://localhost:8080/api/users', formData);
                if (response.status === 200) {
                    notifySuccess();
                }
                console.log('User added:', response.data);
            } catch (error) {
                console.error('There was an error adding the user!', error);
            }
        }
    };

    return (
        <div className="add-user-container">
            <h1>Add User</h1>
            <form onSubmit={handleSubmit} className="add-user-form">
                <div className="form-group">
                    <label className="form-label">Username</label>
                    <input
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        className="form-control form-input"
                    />
                    {errors.username && <p className="error-text">{errors.username}</p>}
                </div>
                <div className="form-group">
                    <label className="form-label">Firstname</label>
                    <input
                        type="text"
                        name="firstname"
                        value={formData.firstname}
                        onChange={handleChange}
                        className="form-control form-input"
                    />
                </div>
                <div className="form-group">
                    <label className="form-label">Lastname</label>
                    <input
                        type="text"
                        name="lastname"
                        value={formData.lastname}
                        onChange={handleChange}
                        className="form-control form-input"
                    />
                </div>                             
                <div className="form-group">
                    <label className="form-label">Phone</label>
                    <input
                        type="phone"
                        name="phone"
                        value={formData.phone}
                        onChange={handleChange}
                        className="form-control form-input"
                    />
                    {errors.phone && <p className="error-text">{errors.phone}</p>}
                </div>
                <div className="form-group">
                    <label className="form-label">Email</label>
                    <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        className="form-control form-input"
                    />
                </div>                
                <div className="form-group">
                    <label className="form-label">Age</label>
                    <input
                        type="age"
                        name="age"
                        value={formData.age}
                        onChange={handleChange}
                        className="form-control form-input"
                    />
                </div>
                <button type="submit" className="btn btn-primary">Add User</button>
            </form>
        </div>
    );
}

export default AddUser;

