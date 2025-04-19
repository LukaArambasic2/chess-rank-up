import axios from "axios";

const baseURL = 'http://localhost:8080/api';

const api = axios.create({
    baseURL: baseURL,
    headers: {
        'Content-Type': "application/json",
    },
});

/*api.interceptors.request.use(
    config => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
    }, error => {
        console.error("This happened!");
        return Promise.reject(error);
    }
);*/

export default api;