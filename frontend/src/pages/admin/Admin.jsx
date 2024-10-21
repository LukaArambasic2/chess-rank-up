import React from "react";
import './Admin.css';
import TitleContainer from "../../components/titleContainer/TitleContainer";

const Admin = () => {
    return (
        <div className="container">
            <TitleContainer title={"Admin"} description={"Dodaj događanja, listu JMBAGova i drugo"} />
        </div>
    );
}

export default Admin;