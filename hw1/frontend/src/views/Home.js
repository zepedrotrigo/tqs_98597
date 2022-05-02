import React from "react";
import { Button, Card } from '@mui/material';
import Container from '../components/Container';
import SideBar from "../components/Sidebar";
import DatePicker from "../components/DatePicker";
import '../App.css';

class Home extends React.Component {

    constructor(props) {
        super(props)
        this.DatePickerHandler = this.DatePickerHandler.bind(this);
        this.state = {
            json: null,
            selectedIndex: 1,
            datePicker: new Date((new Date()) - 1000*60*60*24).toISOString().split('T')[0],
        }
    }

    // This method will be sent to the child component
    DatePickerHandler(date) {
        this.setState({ datePicker: date.toISOString().split('T')[0] });
    }

    fetchData() {
        console.log("LINE 28:" + this.state.datePicker);
        fetch(`http://localhost:8080?date=${this.state.datePicker}`)
            .then((response) => response.json())
            .then((data) => {
                this.setState({ json: data });
            })
        
    }

    //Fetch data first time on page load
    componentDidMount() {
        this.fetchData();
    }

    renderCards() {
        return (
            <div className="grouped-cards">
                <Card variant="outlined"><h5>Last updated:</h5><p>{this.state.json.data.last_update}</p></Card>
                <Card variant="outlined"><h5>Total Confirmed:</h5><p>{this.state.json.data.confirmed}</p></Card>
                <Card variant="outlined"><h5>Total Deaths:</h5><p>{this.state.json.data.deaths}</p></Card>
                <Card variant="outlined"><h5>Total Recovered Cases:</h5><p>{this.state.json.data.recovered}</p></Card>
                <Card variant="outlined"><h5>Confirmed Cases diff yesterday:</h5><p>{this.state.json.data.confirmed_diff}</p></Card>
                <Card variant="outlined"><h5>Deaths diff yesterday:</h5><p>{this.state.json.data.deaths_diff}</p></Card>
                <Card variant="outlined"><h5>Active Cases diff yesterday:</h5><p>{this.state.json.data.active_diff}</p></Card>
                <Card variant="outlined"><h5>Recovered Cases diff yesterday:</h5><p>{this.state.json.data.recovered_diff}</p></Card>
                <Card variant="outlined"><h5>Fatality Rate:</h5><p>{this.state.json.data.fatality_rate}</p></Card>
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
                        <DatePicker action={this.DatePickerHandler} className="input-size"></DatePicker>
                            <Button className="spacing" variant="contained" onClick={this.fetchData.bind(this)}>Search!</Button>
                        </Container>
                        <Container>
                            {this.state.json !== null && this.renderCards()}
                        </Container>
                    </div>
                </div>
            </div >
        );
    }
}

export default Home;