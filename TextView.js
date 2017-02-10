import {PropTypes} from 'react'
import {requireNativeComponent ,View } from 'react-native';
var myTextView ={
    name:'MyTextView',
    propTypes:{
		...View.propTypes,
        text:PropTypes.string,
        textSize:PropTypes.number,
		isAlpha:PropTypes.bool    
    }
}
module.exports =requireNativeComponent('MyTextView',myTextView);