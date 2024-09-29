import React from 'react';
import useAddUser from '../hooks/useAddUser';

function AddUser() {
    const { errors, handleSubmit, handleChange, formData } = useAddUser();

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
                    {errors.username && <p className="error-text" style={{ color: 'red' }}>{errors.username}</p>}
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
                    {errors.phone && <p className="error-text" style={{ color: 'red' }}>{errors.phone}</p>}
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

