import React from "react";
import TextField from '@mui/material/TextField';
import { AdapterMoment } from '@mui/x-date-pickers/AdapterMoment';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';

export default class BasicDatePicker extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            datePicker: new Date((new Date()) - 1000*60*60*24).toISOString().split('T')[0],
        }
    }

    render() {
        return (<LocalizationProvider dateAdapter={AdapterMoment}>
            <DatePicker value={this.state.datePicker} onChange={newValue => {
                this.setState({ datePicker: (newValue).toISOString().split('T')[0] });
                this.props.action(newValue);
            }} renderInput={params => <TextField {...params} />} />
        </LocalizationProvider>);
    }

}