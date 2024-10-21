import React from "react";
import TitleContainer from "../../components/titleContainer/TitleContainer";

const Post = () => {
    return (
        <div className="container">
            <TitleContainer title={"Obavijest"} description={"Autor: " + "\n" + "Vrijeme objave: "} />
            <div className="aboutText">
                <h2>Obavijesti info</h2>
                <article>
                    Ovdje ide tekst
                    Tekst je automatski generiran...
                    Imamo template na kojem se objavljuje samo doda: vrijeme, autor, broj sakupljenih bodova.
                    I onda postoji još opcija postavljanja pravih objava. Nije pretjerano važno, ali mogla bi imati neku zvjezdicu ili nešto što naglašava da je tu objavu važno za pročitati.
                </article>
            </div>
        </div>
    )
}

export default Post;