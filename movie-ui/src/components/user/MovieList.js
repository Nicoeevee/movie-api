import React from 'react'
import {Divider, Form, Grid, Header, Icon, Image, Input, Item, Segment} from 'semantic-ui-react'

function MovieList({isMoviesLoading, movieTextSearch, movies, handleInputChange, handleSearchMovie}) {
  let movieList
  if (movies.length === 0) {
    movieList = <Item key='no-movie'>No Movie</Item>
  } else {
    movieList = movies.map(movie => {
      return (
        <Item as={'a'} key={movie.imdb} href={movie.url} target="_blank">
          <Image src={movie.poster} size='small' bordered rounded/>
          <Item.Content>
            <Item.Header>{movie.title}</Item.Header>
            <Divider/>
            <Item.Meta>{movie.imdb}</Item.Meta>
            <Item.Description>
              <Image src='https://react.semantic-ui.com/images/wireframe/short-paragraph.png'/>
            </Item.Description>
            <Item.Extra>
              <span className="imdbRatingPlugin" data-user="ur125219703" data-title={movie.imdb} data-style="p3"><a
                href={`https://www.imdb.com/title/${movie.imdb}/?ref_=plg_rt_1`}><img
                src="https://ia.media-imdb.com/images/G/01/imdb/plugins/rating/images/imdb_37x18.png" alt={`${movie.name} on IMDb`}/>
</a></span>
              {
                function (d, s, id) {
                  var js, stags = d.getElementsByTagName(s)[0];
                  if (d.getElementById(id)) {
                    return;
                  }
                  js = d.createElement(s);
                  js.id = id;
                  js.src = "https://ia.media-imdb.com/images/G/01/imdb/plugins/rating/js/rating.js";
                  stags.parentNode.insertBefore(js, stags);
                }
                (document, "script", "imdb-rating-api")
              }

            </Item.Extra>
          </Item.Content>
        </Item>
      )
    })
  }

  return (
    <Segment loading={isMoviesLoading} color='purple'>
      <Grid stackable divided>
        <Grid.Row columns='2'>
          <Grid.Column width='3'>
            <Header as='h2'>
              <Icon name='video camera'/>
              <Header.Content>Movies</Header.Content>
            </Header>
          </Grid.Column>
          <Grid.Column>
            <Form onSubmit={handleSearchMovie}>
              <Input
                action={{icon: 'search'}}
                name='movieTextSearch'
                placeholder='Search by IMDB or Title'
                value={movieTextSearch}
                onChange={handleInputChange}
              />
            </Form>
          </Grid.Column>
        </Grid.Row>
      </Grid>
      <Item.Group divided unstackable relaxed link>
        {movieList}
      </Item.Group>
    </Segment>
  )
}

export default MovieList