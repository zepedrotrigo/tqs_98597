import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Home from './views/Home';
import RegionData from './views/RegionData';
import CacheData from './views/CacheData';


function App() {
	// You can put javascript code here or declare constants!
	// use {} inside html to access variables

	return (
		<Router>
			<div className='App'>
				<div className='content'>
					<Routes>
						<Route path="/" element={<Home />} />
						<Route path="/region" element={<RegionData />} />
						<Route path="/cache" element={<CacheData />} />
					</Routes>
				</div>
				<footer></footer>
			</div>
		</Router>
	);
}

export default App;
