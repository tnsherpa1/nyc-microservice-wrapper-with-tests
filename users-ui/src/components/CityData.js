import React, {Component} from 'react'
import axios from 'axios'
import CityList from './CityList'

class CityData extends Component {
    state = {
        city_data: [],
        agencies: []
    }
    filterList = (e) => {
        var updatedList = this.state.city_data

        updatedList = updatedList.filter(function(data){
          return data.agency_name.toLowerCase().search(
            e.target.value.toLowerCase()) !== -1
        });
        this.setState({agencies: updatedList});
    }
    async componentDidMount() {
        try {
            const url = 'https://data.cityofnewyork.us/resource/buex-bi6w.json?category_description=Construction/Construction%20Services&$limit=20'
            const res = await axios.get(`${url}`)
            this.setState({ city_data: res.data})
        } catch(e) {
            console.log(e)
        }
    }
    render() {
        return (
            <div>
                <input className="search-bar" autoFocus={true} type="text" placeholder="Search" onChange={this.filterList} />
                <CityList listings={this.state.agencies} />
            </div>
        )
    }
}

export default CityData