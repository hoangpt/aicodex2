import { render, screen, waitFor } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';
import Home from './Home';

jest.mock('axios');

describe('Home component', () => {
    it('renders the list of users', async () => {
        const users = [
            { id: 1, username: 'user1', fullName: 'User One', phone: '1234567890', email: 'user1@example.com', age: 30 },
            { id: 2, username: 'user2', fullName: 'User Two', phone: '0987654321', email: 'user2@example.com', age: 25 }
        ];
        axios.get.mockResolvedValue({ data: users });

        render(
            <BrowserRouter>
                <Home />
            </BrowserRouter>
        );

        await waitFor(() => {
            expect(screen.getByText('User One')).toBeInTheDocument();
            expect(screen.getByText('User Two')).toBeInTheDocument();
        });
    });

    it('displays an error message when fetching users fails', async () => {
        axios.get.mockRejectedValue(new Error('Error fetching users'));

        render(
            <BrowserRouter>
                <Home />
            </BrowserRouter>
        );

        await waitFor(() => {
            expect(screen.getByText('There was an error fetching the users!')).toBeInTheDocument();
        });
    });

    it('renders the Add User button', () => {
        render(
            <BrowserRouter>
                <Home />
            </BrowserRouter>
        );

        expect(screen.getByText('Add User')).toBeInTheDocument();
    });
});