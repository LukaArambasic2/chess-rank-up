import React, {createContext, useContext, useState} from "react";

const SectionContext = createContext();

export const SectionProvider = ({children}) => {
    const [sectionId, setSectionId] = useState();

    return (
        <SectionContext.Provider value={{sectionId, setSectionId}}>
            {children}
        </SectionContext.Provider>
    );
};

export const useSection = () => useContext(SectionContext);