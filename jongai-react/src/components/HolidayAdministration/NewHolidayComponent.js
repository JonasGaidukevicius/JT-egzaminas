import React from 'react';
import { Link } from 'react-router-dom';
//import PropTypes from 'prop-types';

const NewHolidayComponet = props => {
    //Jeigu yra pasirinkimo sąrašo nuskaitymas iš serverio, tai čia apsirašau kintamąjį su pasirinkimų variantais
    // let optionList = props.typeList.map(v => (
    //     <option key={v}>{v}</option>
    //   ));
    return (
        <div className="container main-data">
            <form onSubmit={props.handleSubmit}>
                <div className="form-row">
                    <div className="col-md-12 col-lg-10 mb-3">
                        <h5>{props.fromMenu}</h5>
                    </div>
                </div>
                <div className="form-row">
                    <div className="col-md-4 col-lg-4 mb-3">
                        <label htmlFor="title">Holiday title:&nbsp;</label>
                        <input type="text" className="form-control" id="title" placeholder="Holiday title"
                                value={props.title} required onChange={props.handleChangeOfTitle}
                                pattern="^[a-zA-ZÀ-ž\s]+$" maxLength="30" title="Must contain only letters up to 30 symbols"></input>
                    </div>
                    <div className="col-md-4 col-lg-4 mb-3">
                        <label htmlFor="image">Image:&nbsp;</label>
                        <input type="text" className="form-control" id="image" placeholder="Image"
                                value={props.image} required onChange={props.handleChangeOfImage}></input>
                    </div>
                </div>
                <div className="form-row">
                    <div className="col-md-8 col-lg-8 mb-3">
                        <label htmlFor="description">Description:&nbsp;</label>
                        <input type="text" className="form-control" id="description" placeholder="Description"
                                value={props.description} required onChange={props.handleChangeOfDescription}
                                pattern="^[a-zA-ZÀ-ž\s]+$" maxLength="50"
                                title="Description must contain only letters up to 50 symbols"></input>
                    </div>
                </div>
                <div className="form-row">
                    <div className="col-md-3 col-lg-3 mb-3">
                        <label htmlFor="type">Type:&nbsp;</label>
                        <select
                            className="form-control-success"
                            id="type"
                            value={props.type}
                            required
                            onChange={props.handleChangeOfType}
                        >
                            <option value="" hidden>Choose option</option>
                            <option value="Valstybinė">Valstybinė</option>
                            <option value="Tautinė-religinė">Tautinė-religinė</option>
                            <option value="Atmintina">Atmintina</option>
                            <option value="Netradicinė">Netradicinė</option>
                            {/* Jeigu yra sąrašas paimtas kaip kintamasis, tada rašoma
                            {optionList} */}
                        </select>
                    </div>
                    <div className="col-md-4 col-lg-4 mb-3">
                        &nbsp;&nbsp;&nbsp;<label className="form-check-label" htmlFor="flag">
                            <input className="form-check-input" type="checkbox" id="flag" value={props.flag} onChange={props.handleChangeOfFlag}></input>
                            Flag rising</label>
                    </div>
                </div>
                <div className="form-row">
                    <div className="col-md-8 col-lg-8 mb-3">
                        <label htmlFor="description">Simple date:&nbsp;</label>
                        <input type="date" className="form-control" id="simpleDate" 
                                value={props.simpleDate} required onChange={props.handleChangeOfSimpleDate}
                        ></input>
                    </div>
                </div>
                <button className="btn btn-primary" type="submit">Save</button>&nbsp;
                <Link to={`/admin/holidays`} className="btn btn-dark" >Cancel</Link>
                {/* perdaryti pagal sita
            <button className="btn btn-success" style={{ marginRight: '20px' }} onClick={this.props.onSaveClick}>Save</button>
            */}
            </form>
        </div>
    );
}

export default NewHolidayComponet;