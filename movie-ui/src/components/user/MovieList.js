import React from 'react'
import {Button, Divider, Form, Grid, Header, Icon, Image, Input, Item, Label, Modal, Segment} from 'semantic-ui-react'

function MovieList({isMoviesLoading, movieTextSearch, movies, handleInputChange, handleSearchMovie, handleAddBookmark, bookmarkName, bookmarkDescription}) {
  let movieList
  const [open, setOpen] = React.useState(false)
  if (movies.length === 0) {
    movieList = <Item key='no-movie'>No Movie</Item>
  } else {
    movieList = movies.map(movie => {
      return (
        <Item key={movie.imdb}>
          <Item.Image as={'a'} href={movie.url} target="_blank" src={movie.poster} size='small' bordered rounded/>
          <Item.Content>
            <Item.Header as={'a'} href={movie.url} target="_blank">{movie.title}</Item.Header>
            <Divider/>
            <Item.Meta>
              <span className="imdbRatingPlugin" data-user="ur125219703" data-title={movie.imdb}
                    data-style="p3"><a
                href={`https://www.imdb.com/title/${movie.imdb}/?ref_=plg_rt_1`}><img
                src="https://ia.media-imdb.com/images/G/01/imdb/plugins/rating/images/imdb_37x18.png"
                alt={`${movie.name} on IMDb`}/>
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
              &nbsp;
              {movie.imdb}
            </Item.Meta>
            <Item.Description>
              <Image src='https://react.semantic-ui.com/images/wireframe/short-paragraph.png'/>
            </Item.Description>
            <Item.Extra>
              <Label>New</Label>
              <Button.Group labeled floated={"right"}>
                <Button icon='play' content='播放' labelPosition='left' href={movie.url} target="_blank"/>
                <Modal
                  as={Form}
                  onClose={() => setOpen(false)}
                  onOpen={() => setOpen(true)}
                  open={open}
                  trigger={<Button icon='right arrow' content='影评' labelPosition='right' primary/>}
                  onSubmit={e => {
                    handleAddBookmark(movie.imdb)
                    setOpen(false)
                  }}
                >
                  <Modal.Header> 为{movie.title}撰写影评</Modal.Header>
                  <Modal.Content image>
                    <Item.Image src={movie.poster} size='small' bordered rounded/>
                    <Modal.Description>
                      <Header>{movie.title}</Header>
                      <p>
                        We've found the following gravatar image associated with your e-mail
                        address.
                      </p>
                      <p>Is it okay to use this photo?</p>
                    </Modal.Description>
                    <Form.Input
                      type="text"
                      label={"标题"}
                      required
                      name='bookmarkName'
                      placeholder='标题'
                      value={bookmarkName}
                      onChange={handleInputChange}
                    />
                    <Form.Input
                      type="text"
                      label={"详情"}
                      required
                      name='bookmarkDescription'
                      placeholder='分享你的感想'
                      value={bookmarkDescription}
                      onChange={handleInputChange}
                    />
                  </Modal.Content>
                  <Modal.Actions>
                    <Button
                      color='black'
                      type='reset'
                      content="取消"
                      // onClick={() => setOpen(false)}
                    >
                    </Button>
                    <Button
                      content="提交"
                      type="submit"
                      labelPosition='right'
                      icon='checkmark'
                      // onClick={() => setOpen(false)}
                      positive
                    />
                  </Modal.Actions>
                </Modal>
              </Button.Group>
            </Item.Extra>
          </Item.Content>
        </Item>
      );
    })
  }

  return (
    <Segment loading={isMoviesLoading} color='yellow'>
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