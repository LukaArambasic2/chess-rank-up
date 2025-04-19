import {createContext, useContext, useState, useEffect} from "react";
import api from "../api";

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [user, setUser] = useState(null);

    const login = async (email, password) => {
        let errorText = null;
        let errorTrue = false;
        await api.post('auth/login', {email, password}).then(response => {
            console.log(response.data);
            localStorage.setItem('token', response.data.token);
            setUser(response.data.user);
        }).catch(error => logout())
    }

    const logout = () => {
        const token = localStorage.getItem('token');
        if (token) localStorage.removeItem('token');
        setUser(null);
    }

    /**
     *
     * TODO: Need to enable interceptors
     * @returns {Promise<void>}
     */
    const checkUser = async () => {
        await api.get('auth/me')
            .then(response => setUser(response.data.user))
            .catch(error => logout());
    }

    useEffect(()=>{
        const token = localStorage.getItem('token');
        if (token) checkUser();
    },[])

    return (
        <AuthContext.Provider value={{ user, login, logout }}>
            {children}
        </AuthContext.Provider>
    )

};

export const useAuth = () => useContext(AuthContext);