/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  TouchableHighlight,
  DeviceEventEmitter,
  ListView,
  Animated,
  TouchableOpacity,
  Easing,
  View
} from 'react-native';

 // require("./css/jquery.mobile.min.css")
  //require("./css/jqm-demos.css")

 // require("./build/react.js")
 // require("./build/react-dom.js")
  //require("./build/browser.min.js")


 // require("http://code.jquery.com/jquery-2.2.2.min.js")
 // require("https://cdnjs.cloudflare.com/ajax/libs/jquery-mobile/1.4.5/jquery.mobile.min.js")
  
  //var App=require("./web/app")
  
import uSDKModule from './uSDKModule'
import RCTImageView from './ImageView'
//var MyTextView = require('./TextView');
import MyTextView from './TextView'
require('./img.jpg')


var Button = React.createClass({
  render:function(){    
    return (<TouchableHighlight
                style={styles.button}
                underlayColor="#a5a5a5">
                <Text>{this.props.text}</Text>               
           </TouchableHighlight>
       )
  }
});


class ListViewBasics extends Component {
  // 初始化模拟数据
  constructor(props) {
    super(props);
    const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
    this.state = {
      dataSource: ds.cloneWithRows([
        'John', 'Joel', 'James', 'Jimmy', 'Jackson', 'Jillian', 'Julie', 'Devin'
      ])
    };
  }
  render() {
    return (
      <View style={{paddingTop: 22}}>
        <ListView
          dataSource={this.state.dataSource}
          renderRow={(rowData) => <Text>{rowData}</Text>}
        />
      </View>
    );
  }
}


class lxTest extends Component {
   constructor(){
        super();  

        this.state={
            text:"hahahahahaghahaha",
            bounceValue:new Animated.Value(0),
            opacityAnmValue : new Animated.Value(0)

        }
    }

   componentDidMount() {
    uSDKModule.testPrint("Jack", {
      height: '1.78m',
      weight: '7kg'
    });

    uSDKModule.getNativeClass(name => {
      console.log("lx sdk nativeClass: ", name);
    });
    
    uSDKModule.start();

    /*uSDKModule.getAllSightedDev(devList => {
      console.log("lx sdk device list "+devList)
    });*/
    /*uSDKModule.getAllSightedDev();*/


/* uSDKModule.getAllSightedDev(true).
  than(result => {
    console.log("lx sdk device list "+result);
  }).catch(result => {
   console.log("lx sdk device catch "+result);
  });*/

    //打印常量的值
    console.log("BGModuleName const value = ", uSDKModule.BGModuleName);
    DeviceEventEmitter.addListener("newDeviceList", info => {
      console.log("lx device list "+info);
    });
 
  }
  


    _onPress() {
        _animateHandler.start && _animateHandler.start()
    }

  render() {
    return (
     <View style={styles.container,styles.row}>
      <Button text="wolcome"/>  
        <Text> 智能设备列表(已被uSDK发现) </Text>  
        <ListViewBasics /> 
        <View  style={styles.outView}>
         <MyTextView style={styles.myTextView}
                text={this.state.text}
                textSize={15}
                isAlpha={false}/> 
        </View>
        <View style={styles.myOutView}>
        <RCTImageView src={[{uri:'http://i.imgur.com/XMKOH81.jpg', width:300, heigth:100}]}
         borderRadius={10} resizeMode='cover'  /> 
        </View>  
                         
      </View> 
   
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
    },

  content: {
        justifyContent: 'center',
        backgroundColor: 'yellow',
    },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  row:{
        flexDirection: 'column',
    },
  button: {
        flex:1,
        margin:10,
        backgroundColor: 'blue',
        borderBottomWidth: StyleSheet.hairlineWidth,
        borderBottomColor: '#cdcdcd',
    },
    outView:{
        borderWidth:1,
    },
    myOutView:{
      marginTop:2,
      borderWidth:3,
      width:300,
      height:100,
      borderColor:'red'
    },
    RCTImageView:{
      
    },
     myTextView:{
        width:300,
        height:100,
    }
});

 //var Compo = require('./web/app');
//AppRegistry.registerComponent('lxTest', () =>  Compo);

AppRegistry.registerComponent('lxTest', () => lxTest);
