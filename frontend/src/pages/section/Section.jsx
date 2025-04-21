import React from "react";
import "./Section.css"
import { useState, useEffect } from "react";
import TitleContainer from "../../components/titleContainer/TitleContainer";
import {useNavigate, useParams} from "react-router-dom";
import api from "../../api";

const Section = () => {
    const [isJoined, setIsJoined] = useState(false);
    const [detailedSection, setDetailedSection] = useState({});
    const nav = useNavigate();
    const {id} = useParams();

    useEffect(() => {
        async function fetchData() {
            await api.get(`/sections/${id}`)
                .then(response => {
                    setDetailedSection(response.data);
                    console.log("Detailed section: ", response.data)
                })
                .catch(error => {
                    console.log("Section error: ", error)
                })
        }
        fetchData();
    }, []);

    const handleClick = () => {
        nav("/profile")
    };

    const sectionDescription = ` Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut ac sodales dolor. Fusce sapien leo, dictum tincidunt orci non, convallis condimentum leo. Nulla rutrum, enim sit amet rutrum condimentum, nisi enim mollis nulla, vel lobortis libero purus quis orci. Donec ante neque, consequat sed mi nec, fringilla ornare lorem. Praesent aliquet risus et tortor sagittis tempor. Pellentesque dignissim elit nec nisl varius auctor. Proin ligula dolor, semper a felis vitae, lacinia mattis magna. Vestibulum dictum dolor eu eros rhoncus egestas. Donec viverra, libero eget tempor tincidunt, ipsum sapien imperdiet sapien, nec molestie augue dolor vel metus. Nullam turpis ex, blandit at nisi sed, accumsan gravida mauris. Cras pulvinar eget magna non tempor. Pellentesque semper mi non augue condimentum volutpat. Fusce in neque pharetra ante finibus pulvinar. Duis ligula tellus, ultricies sit amet vulputate ac, rutrum at diam. Donec eget nisi ut tortor vulputate blandit at vulputate mauris. Maecenas egestas ultrices purus id lobortis. Phasellus tempor ultricies ante in ultrices. Quisque enim lorem, mattis at consectetur quis, scelerisque ut lorem. Phasellus viverra, quam eu lacinia viverra, arcu nibh viverra nibh, quis semper velit erat sit amet odio. Suspendisse finibus vel ipsum sed laoreet. Phasellus eget suscipit velit, id accumsan velit. Sed pharetra placerat quam bibendum sagittis. Duis ac diam lorem. Maecenas auctor vehicula massa non interdum. Nullam imperdiet consectetur lectus in euismod. Vivamus nunc lorem, congue a ipsum et, volutpat accumsan augue. Fusce ornare tincidunt semper. Pellentesque augue erat, condimentum et est molestie, tincidunt feugiat elit. Praesent lobortis dictum enim, at consectetur est blandit ac. Phasellus aliquam elementum tellus. Donec nec efficitur nulla. Nulla lobortis congue accumsan. Phasellus sit amet arcu lacinia, tempus lacus vitae, finibus augue. Phasellus fringilla nulla at arcu pretium, pellentesque congue ex pellentesque. Mauris congue feugiat orci, quis egestas eros tincidunt vitae. Nullam efficitur, neque sed finibus aliquet, erat lectus eleifend mi, in accumsan turpis lectus id lectus. Proin venenatis leo ante, vitae aliquet arcu posuere sed. Aenean dapibus aliquam velit in accumsan. In eget orci egestas, dapibus ipsum id, auctor mi. Nam blandit tortor vel mauris semper porttitor. Donec mollis nulla ut massa ornare, id accumsan sapien placerat. Proin id dolor accumsan, semper dolor a, gravida purus. Cras neque odio, vehicula quis risus et, pellentesque vestibulum nisl. Curabitur quis bibendum justo, eu condimentum justo. `
    return (
        <div className="container">
            <TitleContainer title={detailedSection.name} description={"Info o sekciji"}/>
            <div className="aboutText">
                { detailedSection.descriptionUrl }
            </div>
            <div id="enroll-button-container">
                <button id="enroll-button-green" onClick={()=>handleClick()} disabled={!isJoined} style={isJoined?{backgroundColor:"lightgrey"}:{}}>
                    <p id="enroll-button-text">
                        {isJoined? "Already joined":"Join now"}
                    </p>
                </button>
            </div>
        </div>
    );
}

export default Section;