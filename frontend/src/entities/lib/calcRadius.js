export const calcRadius = (dots, percent) => {
  const lowerDots = [], highDots = [];
  //const dx = x2 - x1;
  //const dy = y2 - y1;
  //const lineLength = Math.sqrt(dx * dx + dy * dy);
  //const distance = percent
  dots.map(item => {
    const xL = item.x - percent * (item.y / Math.sqrt((item.x * item.x + item.y * item.y))),
      xH = item.x + percent * (-item.y / Math.sqrt((item.x * item.x + item.y * item.y)));
    const yL = item.y - percent * (item.x / Math.sqrt(item.x * item.x + item.y * item.y)),
      yH = item.y + percent * (-item.x / Math.sqrt(item.x * item.x + item.y * item.y));
    lowerDots.push({x: xL, y: yL});
    highDots.push({x: xH, y: yH});
  });
  return [lowerDots, highDots];
};