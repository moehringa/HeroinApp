import React, {Component} from 'react';
import { Button, StyleSheet, View, TextInput, TouchableOpacity, Text, StatusBar,
  ToolbarAndroid, FlatList, Alert} from 'react-native';
import { StackNavigator, TabNavigator} from 'react-navigation';
//import { List, ListItem } from 'react-native-elements'

export default class StoryFeed extends React.Component {
  static navigationOptions = {
    title: 'Stories',
  };

  constructor(props){
    super(props);
    this.state={
      loading: false,
      feed: this.props.navigation.state.params.stories,
      page: 1,
      seed: 1,
      error: null,
      refreshing: false,
    }
  }
  FlatListItemSeparator = () => {
    return (
      <View
        style={{
          height: 1,
          width: "100%",
          backgroundColor: "#607D8B",
        }}
      />
    );
  }

GetFlatListItem (title) {

  Alert.alert(title);

  }
  render() {

    return (

      <View style={styles.container}>
      <ToolbarAndroid
      logo={require('../images/logo.jpg')}
      />

      <FlatList style={styles.feed}
        data={ this.state.feed }
        ItemSeparatorComponent = {this.FlatListItemSeparator}
        renderItem={({item}) => <Text style={styles.FlatListItemStyle}
                                  onPress={this.GetFlatListItem.bind(this, item.title)}>
                                  id: {item.userId}{'\n'}
                                  title: {item.title}
                                </Text>}
        keyExtractor={(item, index) => index}
       />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#30C5FF'
  },
  formContainer: {
    flexGrow: 1,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 50,
  },
  navbar: {
    flexDirection: 'row',
    flex: 4,
  },
  buttonContainer: {
    backgroundColor: 'gray',
    paddingVertical: 15,
    marginBottom: 20,
  },
  buttonText: {
    textAlign: 'center',
    color: '#FFFFFF',
    fontWeight: 'bold'
  },
  feed: {
    padding: 10,
  },
});
