import React from 'react';
import NavigationComponent from './NavigationComponent';
import { Route, BrowserRouter, Switch } from "react-router-dom";
import HolidayListContainer from '../HolidayList/HolidayListContainer';
import OneHolidayContainer from '../HolidayList/OneHolidayContainer';
import HolidayAdministrationListContainer from '../HolidayAdministration/HolidayAdministrationListContainer';
import CountryAdministrationContainer from '../CountryAdministration/CountryAdministrationListContainer';
import NewHolidayContainer from '../HolidayAdministration/NewHolidayContainer';
import EditHolidayContainer from '../HolidayAdministration/EditHolidayContainer';
import NewCountryContainer from '../CountryAdministration/NewCountryContainer';
import EditCountryContainer from '../CountryAdministration/EditCountryContainer';
import OneCountryContainer from '../CountryList/OneCountryContainer';
import NewUserContainer from '../UserAdministration/NewUserContainer';

class NavigationContainer extends React.Component {
    //constructor(props) {
        //super(props);
        // this.state = {
        //     user: ""
        // };
    //}

    //Nenaudojamas metodas
    // handleChangeOnName = (event) => {     
    //     this.setState({ user: event.target.value });           
    // }

    render() {
        return (
            //Ankstesnis budas
            //   <NavigationComponent children={this.props.children} userName={this.state.user} handleChangeOnName={this.handleChangeOnName} />
            // Dabar cia atsiranda <BrowserRouter> elementas. Anksciau jis buvo faile App.js
            
            <BrowserRouter>
                {/* Nebūtina siųsti children */}
                <NavigationComponent children={this.props.children} onClickLogoutHandler={this.props.onClickLogoutHandler}>
                    <Switch>
                        <Route path="/" render={props => (
                            <HolidayListContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/holidays/:code" render={props => (
                            <OneHolidayContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/admin" render={props => (
                            <HolidayAdministrationListContainer {...props} />
                        )}
                        exact
                        />
                         <Route path="/admin/country" render={props => (
                            <CountryAdministrationContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/admin/holidays/new" render={props => (
                            <NewHolidayContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/admin/holidays/:code" render={props => (
                            <EditHolidayContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/admin/countries/new" render={props => (
                            <NewCountryContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/admin/countries/:countryCode" render={props => (
                            <EditCountryContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/countries/:countryCode" render={props => (
                            <OneCountryContainer {...props} />
                        )}
                        exact
                        />
                        <Route path="/newUser" render={props => (
                            <NewUserContainer {...props} />
                        )}
                        exact
                        />
                    </Switch>    
                </NavigationComponent>
            </BrowserRouter>
            
                   
        );
    }
}

export default NavigationContainer;