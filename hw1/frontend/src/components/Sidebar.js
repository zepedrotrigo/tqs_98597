import { List, ListItemButton, ListItemIcon, ListItemText } from '@mui/material';
import { Link } from 'react-router-dom';
import PublicIcon from '@mui/icons-material/Public';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import CachedIcon from '@mui/icons-material/Cached';

function SideBar() {
    return (
        <div>
            <h3>COVID-19 TRACKER</h3>
            <List className="list" component="nav" aria-label="main mailbox folders">
                <Link to="/" style={{ color: "black", textDecoration: 'none' }}>
                    <ListItemButton>
                        <ListItemIcon className="test"><PublicIcon /></ListItemIcon>
                        <ListItemText primary="World Data" id="side-bar-opt-0"/>
                    </ListItemButton>
                </Link>
                <Link to="/region" style={{ color: "black", textDecoration: 'none' }}>
                    <ListItemButton>
                        <ListItemIcon><LocationOnIcon /></ListItemIcon>
                        <ListItemText primary="Regional Data" id="side-bar-opt-1" />
                    </ListItemButton>
                </Link>
                <Link to="/cache" style={{ color: "black", textDecoration: 'none' }}>
                    <ListItemButton>
                        <ListItemIcon><CachedIcon /></ListItemIcon>
                        <ListItemText primary="Cache Data" id="side-bar-opt-2"/>
                    </ListItemButton>
                </Link>
            </List>
        </div>
    )
}

export default SideBar;