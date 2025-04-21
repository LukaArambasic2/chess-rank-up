import {createContext, useContext, useState, useEffect} from "react";
import api from "../api";

const AuthContext = createContext();

export const AuthProvider = ({children}) => {
    const [user, setUser] = useState(null);

    const login = async (email, password) => {
        let errorText = null;
        let errorTrue = false;
        console.log("Now here", email, password);
        await api.post('auth/login', {email:email, password:password})
            .then(response => {
                localStorage.setItem('token', response.data.token);
                let user = response.data.user;
                user = {...user, id: response.data.id};
                setUser(user);})
            .catch(error => {
                console.log("Erroroorroroorrorooror")
                logout()
            })
    }

    const logout = () => {
        const token = localStorage.getItem('token');
        if (token) localStorage.removeItem('token');
        setUser(null);
    }

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