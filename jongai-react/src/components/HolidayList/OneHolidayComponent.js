import React from 'react';
//import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import './styles.css';

const OneHolidayComponent = (props) => {
    let optionAddedCountryList = props.addedCountries.map(v => (
        <option key={v}>{v}</option>
    ));
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-2 col-lg-2">
                    <div className="row">
                        <h5>Holiday's picture:</h5>
                    </div>
                    <div className="row">
                        <img src={"/img/holidays/" + props.image} alt="Holiday_picture" width="150px"></img>
                    </div>
                </div>
                <div className="col-md-3 col-lg-3">
                    <div className="row">
                        <h5>Holiday's title:</h5>
                    </div>
                    <div className="row pabraukti" >
                        <p>{props.title}</p>
                    </div>
                    <div className="row">
                        <h5>Holiday's description:</h5>
                    </div>
                    <div className="row pabraukti">
                        <p>{props.description}</p>
                    </div>
                    <div className="row">
                        <h5>Holiday's type:</h5>
                    </div>
                    <div className="row pabraukti">
                        <p>{props.type}</p>
                    </div>
                    <div className="row">
                        <h5>Raise flag:</h5>
                    </div>
                    <div className="row pabraukti">
                        <p>{props.flag}</p>
                    </div>
                    <div className="row pabraukti">
                        <p>{props.simpleDate}</p>
                    </div>
                    <div className="row">
                        <Link to={`/`} className="btn btn-dark mt-3">Back</Link>
                    </div>
                </div>
                <div className="col-md-3 col-lg-3">
                    <p>Jau priskirtos šalys</p>
                    <select className="form-control rounded"
                          multiple
                          size="5"
                          onChange={props.countryRemovingHandler}>
                        {optionAddedCountryList}
                    </select>
                    <button className="btn btn-primary" onClick={props.removeCountriesFromHoliday}>Pašalinti šalis</button>&nbsp;

                    <p>Esamos šalys šalys</p>
                    <select className="form-control rounded"
                          multiple
                          size="5"
                          onChange={props.availableCountrySelectionHandler}>
                        {props.showAvailableCountries()}
                    </select>
                    <button className="btn btn-primary" onClick={props.addCountriesToHoliday}>Pridėti šalis</button>&nbsp;
                </div>
            </div>
        </div>
    );
}

/*
OneProductComponent.propTypes = {
    id:PropTypes.number.isRequired,
    title: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    quantity: PropTypes.number.isRequired
};*/


export default OneHolidayComponent;