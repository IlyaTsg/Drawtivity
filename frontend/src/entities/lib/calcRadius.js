export const calcRadius = (dots, percent) => {
    const lowerDots = [], highDots = []
    //const dx = x2 - x1;
    //const dy = y2 - y1;
    //const lineLength = Math.sqrt(dx * dx + dy * dy);
    //const distance = percent
    dots.map(item => {

        const yL = item.y === 0 ? item.y - Number(percent)  :item.y - Number(percent),
            yH= item.y === 0 ? item.y + Number(percent)  : item.y + Number(percent)
        lowerDots.push({x: item.x, y: yL})
        highDots.push({x: item.x, y: yH})
    })
    return [lowerDots, highDots]
}