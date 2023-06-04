export const calcRadius = (dots, percent) => {
    const lowerDots = [], highDots = []
    dots.map(item => {
        const yL = item.y === 0 ? item.y - Number(percent) :item.y - item.y * Number(percent/100),
            yH= item.y === 0 ? item.y + Number(percent) :item.y + item.y * Number(percent/100)
        lowerDots.push({x: item.x, y: yL})
        highDots.push({x: item.x, y: yH})
    })
    return [lowerDots, highDots]
}