import axios from 'axios';

const API_URL = process.env.REACT_APP_API_URL; // read from .env, stateless/parameterized
const API_USERS = API_URL + '/users';

export const getUsers = async () => {
    try {
        const response = await axios.get(API_USERS);
        return response.data;
    } catch (error) {
        console.error('There was an error fetching the users!', error);
        throw error;
    }
};

export const addUser = async (userData) => {
    try {
        return await axios.post(API_USERS, userData);
    } catch (error) {
        console.error('There was an error adding the user!', error);
        throw error;
    }
};