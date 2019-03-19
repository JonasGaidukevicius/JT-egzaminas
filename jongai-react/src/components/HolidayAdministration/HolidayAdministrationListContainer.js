import React from 'react';
//import PropTypes from 'prop-types';
import HolidayAdministrationLineComponent from './HolidayAdministrationLineComponent';
//import MyProvider from '../App';
import axios from 'axios';
import { Link } from 'react-router-dom';

class HolidayAdministrationListContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            holidaysList: '',
            loading: 'Loading holidays. Please wait...'
        };
    }

    

    handleDelete = (title) => {
        console.log("Noriu ištrinti " + this.state.oldTitle);
        axios.delete('http://localhost:8080/holidays/' + (title))
          .then(response => {
            axios.get('http://localhost:8080/holidays')
            .then((response) => {
                this.setState({ holidays: response.data });
            })
            .catch((error) => {
                console.log(error);
            });
          })
          .catch(function (error) {
            console.log(error);
        });
    }

    componentDidMount() {
        axios.get('http://localhost:8080/holidays')
            .then((response) => {
                this.setState({ holidaysList: response.data });
                //console.log(response.data);
                //console.log("Produktai yra - " + this.state.holidays);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    render() {
        if (this.state.holidaysList) {
            const HolidayCards = this.state.holidaysList.map((product, index) => {
                return (
                    <HolidayAdministrationLineComponent
                        key={index}
                        title={product.title}
                        image={product.image}
                        description={product.description}
                        handleDelete={this.handleDelete}
                    />
                );
            });
            return (
                <div className="container">
                    <div className="card">
                        <div className="card-header">
                            <h6 className="text-uppercase mb-0">Švenčių administravimas</h6>
                        </div>
                        <div className="card-body">
                            <div className="row">
                                <Link className="btn btn-success" to="/admin/holidays/new">Add new holiday</Link>
                            </div>
                            <div className="row">
                                <div className="col-12">
                                    <table
                                        className="table table-striped"
                                        // style={{ width: "100%" }}
                                    >
                                        <thead className="thead-inverse">
                                            <tr>
                                                <th>Holiday</th>
                                                <th width="30%">Picture</th>
                                                <th>Description</th>
                                                <th>Operation</th>
                                            </tr>
                                        </thead>
                                        <tbody>{HolidayCards}</tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                // SENAS DIV BŪDAS
                // <div className="container">
                //     <div className="row">
                //          <Link className="btn btn-success" to="/admin/holidays/new">Add new holiday</Link>
                //          </div>
                //          <div className="row">
                //             <div className="col-sm-3 col-md-2 col-lg-2 mb-3">
                //                 <p>Holiday</p>
                //             </div>
                //             <div className="col-sm-3 col-md-2 col-lg-2 mb-3">
                //                 <p>Picture</p>
                //             </div>
                //             <div className="col-sm-6 col-md-8 col-lg-8 mb-3">
                //                 <p>Description</p>
                //             </div>
                //         </div>
                //         <div className="row">{productCards}
                //     </div>
                // </div>
            );
        }
        return this.state.loading;
    }
}

export default HolidayAdministrationListContainer;
