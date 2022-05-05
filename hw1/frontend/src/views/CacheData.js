import React from "react";
import { Select, Button, Card } from '@mui/material';
import '../App.css';
import Container from '../components/Container';
import SideBar from '../components/Sidebar';

class CacheData extends React.Component {

    constructor(props) {
        super(props)
        this.state = {
            cacheData: null,
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/v1/cache')
            .then((response) => response.json())
            .then((data) => {
                this.setState({ cacheData: data })
            })
    }

    renderCards() {
        return (
            <div className="grouped-cards">
                <Card variant="outlined"><h5>Requests:</h5><p id="cache-requests-val">{this.state.cacheData.requests}</p></Card>
                <Card variant="outlined"><h5>Hits:</h5><p>{this.state.cacheData.hits}</p></Card>
                <Card variant="outlined"><h5>Misses:</h5><p>{this.state.cacheData.misses}</p></Card>
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
                            <h3>Cache Stats:</h3>
                            {this.state.cacheData !== null && this.renderCards()}
                        </Container>
                    </div>
                </div>
            </div >
        );
    }
}

export default CacheData;