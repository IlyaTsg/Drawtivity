import { Card, CardContent, CardMedia, Typography } from '@mui/material'


const DocCard = ({ props }) => {
  const { title, text } = props
  return (
    <Card sx={{ width: 580 }}>
      <CardMedia
        sx={{ height: 280 }}
        image="/static/images/cards/contemplative-reptile.jpg"
        title="green iguana"
      />
      <CardContent>
        <Typography
          gutterBottom
          variant="h5"
          component="div"
        >
          {title}
        </Typography>
        <Typography
          variant="body2"
          color="text.secondary"
          textAlign={'justify'}
          fontSize={18}
        >
          {text}
        </Typography>
      </CardContent>
    </Card>
  )
}

export default DocCard