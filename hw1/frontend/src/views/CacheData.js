import React from "react";
import { Select, Button, MenuItem } from '@mui/material';
import '../App.css';
import Container from '../components/Container';
import SideBar from '../components/Sidebar';

class CacheData extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            apiResponded: false,
            json: null,
            selectedIndex: 1,
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/regions/')
            .then((response) => response.json())
            .then((data) => {
                this.setState({ apiResponded: true, json: data })
            })
    }

    populateRegionSelector() {
        let values = [];

        Array.from(this.state.json.data.entries()).map((entry) => {
            const [_, v] = entry;
            values.push(<MenuItem value={v.iso}>{v.name}</MenuItem>);
        })

        return values;
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
                            <h3>Select Country:</h3>
                            <Select defaultValue={'CHN'} label="China">
                                {this.state.apiResponded === true && this.populateRegionSelector()}
                            </Select>
                            <Button variant="contained">Search!</Button>
                        </Container>
                    </div>
                </div>
            </div >
        );
    }
}

export default CacheData;