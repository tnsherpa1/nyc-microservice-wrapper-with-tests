import React, {Component} from 'react'
import City from './City'

class CityList extends Component {
    render() {
        const listComponents = this.props.listings.map((listing, index)=>{
            return <City name={listing.agency_name} 
                         title={listing.short_title}
                         contact_name={listing.contact_name}
                         contact_email={listing.email}
                         contact_tel={listing.contact_phone}
                         start_date={listing.start_date}
                         index={index}
                         />
        })
        return (
            <div>
            {listComponents}
            </div>
        )
    }
}

export default CityList