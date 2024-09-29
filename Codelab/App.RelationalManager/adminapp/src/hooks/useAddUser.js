import { useState } from 'react';
import { toast } from 'react-toastify';
import { addUser } from '../providers/User';

const useAddUser = () => {
    const [errors, setErrors] = useState({});
    const [formData, setFormData] = useState({
        username: '',
        firstname: '',
        lastname: '',
        phone: '',
        email: '',
    });

    // TODO: write to interceptor
    const notifySuccess = () => {
        toast.success('User added successfully!', {
            autoClose: 2000,
        });
    };

    const validate = (formData) => {
        let tempErrors = {};
        tempErrors.username = formData.username ? "" : "Username is required.";
        tempErrors.phone = formData.phone ? (formData.phone.match(/^\d{10}$/) ? "" : "Phone must be 10 digits.") : "Phone is required.";
        setErrors(tempErrors);
        return Object.values(tempErrors).every(x => x === "");
    };

    const handleSubmit = async (formData) => {
        if (validate(formData)) {
            try {
                const response = await addUser(formData);
                if (response.status === 200) {
                    notifySuccess();
                }
                console.log('User added:', response.data);
            } catch (error) {
                console.error('There was an error adding the user!', error);
            }
        }
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
        validate({ ...formData, [name]: value });
    };

    return { errors, handleSubmit, handleChange, formData };
};

export default useAddUser;