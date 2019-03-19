import React from 'react';
//import PropTypes from 'prop-types';
import HolidayCardComponent from './HolidayCardComponent';
import axios from 'axios';

class HolidayListContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            holidaysList: '',
            loading: 'Loading holidays. Please wait...'
        };
    }

    componentDidMount() {
        axios.get('http://localhost:8080/holidays')
            .then((response) => {
                this.setState({ holidaysList: response.data });
                console.log("Koks atiduodamas švenčių sąrašas?");
                console.log(this.state.holidaysList);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    render() {
        if (this.state.holidaysList) {
            const holidayCards = this.state.holidaysList.map((holiday, index) => {
                return (
                    <HolidayCardComponent
                        key={index}
                        title={holiday.title}
                        description={holiday.description}
                        image={holiday.image}
                        type={holiday.type}
                        flag={holiday.flag === true ? "Yes" : "No"}
                    />
                );
            });
            return (
                <div className="container">
                    <div className="row">{holidayCards}</div>
                </div>
            );
        }
        return this.state.loading;
    }
}

export default HolidayListContainer;