import React, {useState} from "react";
import TopTri from "../../components/TopTri";
import Mjesto from "../../components/ranking/Mjesto";
import './Scoreboard.css';

const Scoreboard = () => {
    const [type, setType] = useState('semestar');

    const getMjesta = () => {
        switch (type) {
            case 'semestar':
                return [
                    { place: 1, firstName: "Ime", lastName: "Prezime", points: 10 },
                    { place: 2, firstName: "Ime", lastName: "Prezime", points: 8 },
                    { place: 3, firstName: "Ime", lastName: "Prezime", points: 6 },
                    { place: 4, firstName: "Ime", lastName: "Prezime", points: 5 },
                    { place: 5, firstName: "Ime", lastName: "Prezime", points: 4 },
                    { place: 6, firstName: "Ime", lastName: "Prezime", points: 1 }
                ];
            case 'godina':
                return [
                    { place: 1, firstName: "Ime", lastName: "Prezime", points: 50 },
                    { place: 2, firstName: "Ime", lastName: "Prezime", points: 40 },
                    { place: 3, firstName: "Ime", lastName: "Prezime", points: 30 },
                    { place: 4, firstName: "Ime", lastName: "Prezime", points: 20 },
                    { place: 5, firstName: "Ime", lastName: "Prezime", points: 10 },
                ];
            case 'ukupno':
                return [
                    { place: 1, firstName: "Ime", lastName: "Prezime", points: 100 },
                    { place: 2, firstName: "Ime", lastName: "Prezime", points: 80 },
                    { place: 3, firstName: "Ime", lastName: "Prezime", points: 60 },
                    { place: 4, firstName: "Ime", lastName: "Prezime", points: 40 },
                    { place: 5, firstName: "Ime", lastName: "Prezime", points: 20 },,
                    { place: 6, firstName: "Ime", lastName: "Prezime", points: 10 },
                    { place: 7, firstName: "Ime", lastName: "Prezime", points: 5 },
                ];
            default:
                return [];
        }
    };

    const mjesta = getMjesta();
    const topTriMjesta = mjesta.slice(0, 3);
    const preostalaMjesta = mjesta.slice(3);

    return (
        <>
            <div className="up2">
                <div className="sbb">SCOREBOARD 
                <div className="tab">
                    <button className={type === 'semestar' ? 'active' : ''} onClick={() => setType('semestar')}>Semestar</button>
                    <button className={type === 'godina' ? 'active' : ''} onClick={() => setType('godina')}>Godina</button>
                    <button className={type === 'ukupno' ? 'active' : ''} onClick={() => setType('ukupno')}>Ukupno</button>
                </div>
                </div>
                
                <TopTri mjesta={topTriMjesta} />
            </div>

            <div className="content">

                <div className="mjesta">
                    {preostalaMjesta.map(mjesto => (
                        <Mjesto mjesto={mjesto} key={mjesto.place} />
                    ))}
                </div>
            </div>
        </>
    );
}

export default Scoreboard;
