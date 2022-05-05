import React from "react";
import { Select, Button, MenuItem, Card } from '@mui/material';
import '../App.css';
import Container from '../components/Container';
import SideBar from '../components/Sidebar';
import DatePicker from '../components/DatePicker';

class RegionData extends React.Component {

    constructor(props) {
        super(props)
        this.DatePickerHandler = this.DatePickerHandler.bind(this);
        this.state = {
            selectedCountry: "Portugal",
            datePicker: new Date((new Date()) - 1000 * 60 * 60 * 24).toISOString().split('T')[0],
            countriesList: null,
            countryStats: null,
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/v1/countries_list/')
            .then((response) => response.json())
            .then((data) => {
                this.setState({ countriesList: data })
            })

        fetch('http://localhost:8080/v1/country_stats?country=' + this.state.selectedCountry + '&date=' + this.state.datePicker)
            .then((response) => response.json())
            .then((data) => {
                this.setState({ countryStats: data.response[0] })
            })
    }

    // This method will be sent to the child component
    DatePickerHandler(date) {
        this.setState({ datePicker: date.toISOString().split('T')[0] });
    }

    _handleChangeSelectedCountry = (event) => {
        this.setState({ selectedCountry: event.target.value })
    }

    populateCountriesSelector() {
        let values = [];

        Array.from(this.state.countriesList.response.entries()).map((entry) => {
            const [_, v] = entry;
            values.push(<MenuItem value={v}>{v}</MenuItem>);
        })

        return values;
    }

    getCountryStats() {
        fetch('http://localhost:8080/v1/country_stats?country=' + this.state.selectedCountry + '&date=' + this.state.datePicker)
            .then((response) => response.json())
            .then((data) => {
                this.setState({ countryStats: data.response[0] })
            })
    }

    renderCountryStats() {
        return (
            <div className="grouped-cards">
                <Card variant="outlined"><h5>Population:</h5><p id="country-population-val">{this.state.countryStats.population}</p></Card>
                <Card variant="outlined"><h5>Total Cases:</h5><p>{this.state.countryStats.cases.total}</p></Card>
                <Card variant="outlined"><h5>New Cases:</h5><p>{this.state.countryStats.cases.new}</p></Card>
                <Card variant="outlined"><h5>Active Cases:</h5><p>{this.state.countryStats.cases.active}</p></Card>
                <Card variant="outlined"><h5>Recovered Cases:</h5><p>{this.state.countryStats.cases.recovered}</p></Card>
                <Card variant="outlined"><h5>Critical Cases:</h5><p>{this.state.countryStats.cases.critical}</p></Card>
                <Card variant="outlined"><h5>Total Deaths:</h5><p>{this.state.countryStats.deaths.total}</p></Card>
                <Card variant="outlined"><h5>New Deaths:</h5><p>{this.state.countryStats.deaths.new}</p></Card>
                <Card variant="outlined"><h5>Total Tests:</h5><p>{this.state.countryStats.tests.total}</p></Card>
            </div>
        )
    }

    render() {
        return (
            <div>
                <div className="layout">
                    <Container extClass="container sidebar">
                        <SideBar></SideBar>
                    </Container>
                    <div>
                        <Container>
                            <Select id="country-select" className="input-size" onChange={this._handleChangeSelectedCountry} defaultValue={'Portugal'} label="Portugal">
                                {this.state.countriesList !== null && this.populateCountriesSelector()}
                            </Select>
                            <DatePicker className="input-size" action={this.DatePickerHandler}></DatePicker>
                            <Button id="search-btn" className="spacing" variant="contained" onClick={this.getCountryStats.bind(this)}>Search!</Button>
                        </Container>
                        <Container>
                            {this.state.countryStats !== null && this.renderCountryStats()}
                        </Container>
                    </div>
                </div>
            </div >
        );
    }
}

export default RegionData;