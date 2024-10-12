import React from "react";
import TitleContainer from "../../components/titleContainer/TitleContainer";

const Post = ({post}) => {
    return (
        <div className="container">
            <TitleContainer title={post.title} description={post.author + "\n" + post.time} />

        </div>
    )
}

export default Post;