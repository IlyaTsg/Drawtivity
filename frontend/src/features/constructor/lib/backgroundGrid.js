export const createGrid = (context) => {
  const LINE_TYPES = 3;
  const beginPos = [0.5, 50.5, 0.5];
  const step = [50, 100, 25];
  const style = ['rgb(0,0,0)', 'rgb(150,150,150)', 'rgb(150,150,150)'];

  function handler() {
    context.canvas.width = 750;
    context.canvas.height = 551;

    //context.fillStyle = 'rgb(245, 245, 245)';
    //context.fillRect(0, 0, context.canvas.width, context.canvas.height);

    for (let lineType = 0; lineType < LINE_TYPES; ++lineType)
      for (let dir = 0; dir < 2; ++dir) {
        context.beginPath();
        context.lineWidth = 0.2;
        context.strokeStyle = style[lineType];
        let index = beginPos[lineType];
        if (dir == 0)
          while (index < context.canvas.width) {
            context.moveTo(index, 0);
            context.lineTo(index, context.canvas.height);
            index += step[lineType];
          }
        else
          while (index < context.canvas.height) {
            context.moveTo(0, index);
            context.lineTo(context.canvas.width, index);
            index += step[lineType];
          }
        context.stroke();
      }
    /*const colShift = 5, rowShift = 15;
    context.font = '13px Arial bold';
    context.fillStyle = 'black';
    let col = 0;
    while (col + colShift < context.canvas.width) {
      if (col === 0) {
        col += step[step.length - 1];
        continue;
      }
      context.fillText(col, col - 10, 0 + 540);
      col += 50;
    }

    context.fillStyle = 'black';
    let row = 0;
    while (row + rowShift < context.canvas.height) {
      context.fillText(275 - row, 0 + 720, row + rowShift);
      row += 50;
    }*/
  }

  handler();
};

export const cleanAll = (context) => {
  context.canvas.width = 750;
  context.canvas.height = 551;

  //context.fillStyle = 'rgb(245, 245, 245)';
  context.clearRect(0, 0, 750, 550);
};