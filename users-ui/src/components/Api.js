import React, {Component} from 'react'
import axios from 'axios'
import CityList from './CityList'

class Api extends Component {
    state = {
        city_data: []
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
            <CityList listings={this.state.city_data} />
        )
    }
}

export default Api