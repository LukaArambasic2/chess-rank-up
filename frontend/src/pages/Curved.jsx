import React from 'react';

const Curved = () => {
    return (
        <svg width="100vw" height="25vh" viewBox="0 0 1000 1">
            <defs>
                <clipPath id="clip">
                    <path d="M0,100 Q500,0 1000,100 V200 H0 Z" />
                </clipPath>
            </defs>
            <rect width="10000" height="2000" fill="#FFFFFF" clipPath="url(#clip)" />
        </svg>
    );
};

export default Curved;
